import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const _00f3eafd = () => import('../pages/signup/index.vue' /* webpackChunkName: "pages/signup/index" */).then(m => m.default || m)
const _2e5f0dbe = () => import('../pages/events/index.vue' /* webpackChunkName: "pages/events/index" */).then(m => m.default || m)
const _46dc8dc8 = () => import('../pages/applications/index.vue' /* webpackChunkName: "pages/applications/index" */).then(m => m.default || m)
const _ce3186e8 = () => import('../pages/events/new.vue' /* webpackChunkName: "pages/events/new" */).then(m => m.default || m)
const _38440778 = () => import('../pages/signup/finish.vue' /* webpackChunkName: "pages/signup/finish" */).then(m => m.default || m)
const _cb793ba0 = () => import('../pages/applications/_id.vue' /* webpackChunkName: "pages/applications/_id" */).then(m => m.default || m)
const _1f5f19a9 = () => import('../pages/events/_id/index.vue' /* webpackChunkName: "pages/events/_id/index" */).then(m => m.default || m)
const _a142322c = () => import('../pages/events/_id/pattern/index.vue' /* webpackChunkName: "pages/events/_id/pattern/index" */).then(m => m.default || m)
const _0fb91e90 = () => import('../pages/events/_id/pattern/new.vue' /* webpackChunkName: "pages/events/_id/pattern/new" */).then(m => m.default || m)
const _5e6400e0 = () => import('../pages/index.vue' /* webpackChunkName: "pages/index" */).then(m => m.default || m)



if (process.client) {
  window.history.scrollRestoration = 'manual'
}
const scrollBehavior = function (to, from, savedPosition) {
  // if the returned position is falsy or an empty object,
  // will retain current scroll position.
  let position = false

  // if no children detected
  if (to.matched.length < 2) {
    // scroll to the top of the page
    position = { x: 0, y: 0 }
  } else if (to.matched.some((r) => r.components.default.options.scrollToTop)) {
    // if one of the children has scrollToTop option set to true
    position = { x: 0, y: 0 }
  }

  // savedPosition is only available for popstate navigations (back button)
  if (savedPosition) {
    position = savedPosition
  }

  return new Promise(resolve => {
    // wait for the out transition to complete (if necessary)
    window.$nuxt.$once('triggerScroll', () => {
      // coords will be used if no selector is provided,
      // or if the selector didn't match any element.
      if (to.hash) {
        let hash = to.hash
        // CSS.escape() is not supported with IE and Edge.
        if (typeof window.CSS !== 'undefined' && typeof window.CSS.escape !== 'undefined') {
          hash = '#' + window.CSS.escape(hash.substr(1))
        }
        try {
          if (document.querySelector(hash)) {
            // scroll to anchor by returning the selector
            position = { selector: hash }
          }
        } catch (e) {
          console.warn('Failed to save scroll position. Please add CSS.escape() polyfill (https://github.com/mathiasbynens/CSS.escape).')
        }
      }
      resolve(position)
    })
  })
}


export function createRouter () {
  return new Router({
    mode: 'history',
    base: '/',
    linkActiveClass: 'nuxt-link-active',
    linkExactActiveClass: 'nuxt-link-exact-active',
    scrollBehavior,
    routes: [
		{
			path: "/signup",
			component: _00f3eafd,
			name: "signup"
		},
		{
			path: "/events",
			component: _2e5f0dbe,
			name: "events"
		},
		{
			path: "/applications",
			component: _46dc8dc8,
			name: "applications"
		},
		{
			path: "/events/new",
			component: _ce3186e8,
			name: "events-new"
		},
		{
			path: "/signup/finish",
			component: _38440778,
			name: "signup-finish"
		},
		{
			path: "/applications/:id",
			component: _cb793ba0,
			name: "applications-id"
		},
		{
			path: "/events/:id",
			component: _1f5f19a9,
			name: "events-id"
		},
		{
			path: "/events/:id/pattern",
			component: _a142322c,
			name: "events-id-pattern"
		},
		{
			path: "/events/:id/pattern/new",
			component: _0fb91e90,
			name: "events-id-pattern-new"
		},
		{
			path: "/",
			component: _5e6400e0,
			name: "index"
		}
    ],
    
    
    fallback: false
  })
}
