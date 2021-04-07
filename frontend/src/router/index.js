import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store/store';

import Products from '../components/Products/Products';
import Items from '../components/Items/Items';
import Information from '../components/Information/Information';


Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Products',
    component: Products,
    beforeEnter: (to, from, next) => {
      store.dispatch('category/fetchCategory')
        .then(() => next())
        .catch(err => console.error(err));
    },
  },
  {
    path: '/:category/items',
    name: 'Items',
    component: Items,
    beforeEnter: (to, from, next) => {
      store.dispatch('option/getOption', to.params.category)
        .then(() => next())
        .catch(err => console.error(err));
    },
  },
  {
    path: '/items/information',
    name: 'Information',
    component: Information,
  },

];

const router = new VueRouter({
  mode: 'history',
  base: '/',
  routes,
});

export default router;