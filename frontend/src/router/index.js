import Vue from 'vue'
import Router from 'vue-router'

import Root from '@/components/Root'
import Blog from '@/components/items/blog/Blog'
import BlogDetail from '@/components/items/blog/BlogDetail'
import Editor from '@/components/common/editor/Editor'
import Error from '@/components/common/error/404'
import Login from '@/components/common/login/Login'

Vue.use(Router)
const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Root
    },
    {
      path: '/login/:appName',
      name: 'Login',
      component: Login
    },
    {
      path: '/blog',
      name: 'Blog',
      component: Blog,
      children: [],
      beforeEnter: (to, from, next) => {
        console.log('Routing in Blog')
        next()
      }
    },
    {
      path: '/blog/:nickname/:idx',
      name: 'BlogDetail',
      component: BlogDetail,
      beforeEnter: (to, from, next) => {
        console.log('Routing in BlogDetail')
        next()
      }
    },
    {
      path: '/editor',
      name: 'Editor',
      component: Editor
    },
    {
      path: '/redirect-me', redirect: {name: 'Home'}
    },
    // {
    //   path: '*', redirect: {name: '404'}
    // },
    // Error Route
    {
      path: '/error',
      name: 'Error',
      component: Error,
      children: [
        {
          path: ':type',
          component: Error
        }
      ]
    }
  ],
  mode: 'history',
  scrollBehavior (to, from, savedPosition) {
    console.log('Global scrollBehavior')
    document.title = 'Hi-Cord : ' + to.name
    // Having SavedPosition
    if (savedPosition) {
      return savedPosition
    } else if (to.hash) {
      // Having to Hash
      console.log('to hash : ' + to.hash)
      return {
        selector: to.hash
      }
    }
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve({ x: 0, y: 0 })
      }, 500)
    })
  }
})

export default router
