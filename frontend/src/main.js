import Vue from 'vue';
import App from './App.vue';

import store from './store/store';
import router from './router/index';

import axios from 'axios';

axios.defaults.baseURL = 'http://3.141.250.102:8080';

Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
