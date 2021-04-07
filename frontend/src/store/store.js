import Vue from 'vue';
import Vuex from 'vuex';

import createPersistedState from "vuex-persistedstate";

import category from './modules/category';
import option from './modules/option';
import item from './modules/item';
import information from './modules/information';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    category,
    option,
    item,
    information,
  },
  plugins: [createPersistedState()],
});

export default store;