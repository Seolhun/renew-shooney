import Vue from 'vue'
import Router from 'vue-router'

import Root from '@/components/Root.vue'
import Content from '@/components/items/content/Content.vue'
import ContentDetail from '@/components/items/content/ContentDetail.vue'
import Editor from '@/components/common/editor/Editor.vue'
import Error from '@/components/common/error/404.vue'
import Login from '@/components/common/login/Login.vue'

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
      path: '/content',
      name: 'Content',
      component: Content,
      children: [],
      beforeEnter: (to, from, next) => {
        console.log('Routing in Content')
        next()
      }
    },
    {
      path: '/content/:nickname/:idx',
      name: 'ContentDetail',
      component: ContentDetail,
      beforeEnter: (to, from, next) => {
        console.log('Routing in ContentDetail')
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
    document.title = 'Hi-Cord : ' + to.name
    // Having SavedPosition
    if (savedPosition) {
      return savedPosition
    } else if (to.hash) {
      // Having to Hash
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
