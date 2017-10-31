import Vue from 'vue'
import Router from 'vue-router'

import Root from '@/components/Root'
import Notice from '@/components/items/notice/Notice'
import Blog from '@/components/items/blog/Blog'
import BlogDetail from '@/components/items/blog/BlogDetail'
import Me from '@/components/items/aboutme/Me'
import Supporter from '@/components/items/supporter/Supporter'
import MarkdownEditor from '@/components/common/editor/MarkdownEditor'
import Error from '@/components/common/error/404.vue'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Root
    },
    {
      path: '/notice',
      name: 'Notice',
      component: Notice
    },
    {
      path: '/blog',
      name: 'Blog',
      component: Blog,
      children: [
        {
          path: ':type/:id',
          component: BlogDetail,
          beforeEnter: (to, from, next) => {
            console.log('Inside route setup')
            next()
          }},
        {
          path: ':type/:id/edit',
          name: 'BlogEdit',
          component: MarkdownEditor
        }
      ]
    },
    {
      path: '/aboutme',
      name: 'About Me',
      component: Me
    },
    {
      path: '/supporters',
      name: 'Supporters',
      component: Supporter
    },
    {
      path: '/editor',
      name: 'Markdown',
      component: MarkdownEditor
    },
    // Redirect Route
    {
      path: '/redirect-me', redirect: {name: 'Home'}
    }, {
      path: '*', redirect: {name: '404'}
    },
    // Error Route
    {
      path: '/error',
      name: 'Error',
      component: Error,
      children: [
        {
          path: ':type',
          component: Error,
          beforeEnter: (to, from, next) => {
            console.log('Inside route setup')
            next()
          }
        }
      ]
    }
  ],
  mode: 'history',
  scrollBehavior (to, from, savedPosition) {
    document.title = 'Hi-Cord : ' + to.name
    if (savedPosition) {
      return savedPosition
    } else if (to.hash) {
      return {
        selector: to.hash
      }
    }
    return {x: 0, y: 700}
  }
})
