import Vue from 'vue';
import VueCookies from 'vue-cookies';
import App from './App.vue';
import store from './store';
import router from './router';

Vue.use(VueCookies);

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app');
