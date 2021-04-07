import axios from 'axios';

const LIMIT = 6;

const item = {
  namespaced: true,
  state: {
    totalItem: 0,
    items: [],
  },
  getters: {
    totalPage(state) {
      return parseInt((state.totalItem - 1) / 6) + 1;
    },
    cntItems(state) {
      return state.items.length - 1;
    },
    currentPage(state, getters) {
      return parseInt(getters.cntItems / LIMIT) + 1;
    },
  },
  mutations: {
    getItems(state, data) {
      state.items = state.items.concat(data);
    },
    setItems(state, data) {
      state.items = data; 
    },
    getTotal(state, data) {
      state.totalItem = data;
    },
  },
  actions: {
    getItems({commit}, data) {
      const url = `/category/${data.category}/items/page/${data.nextPage}`;
      axios
        .get(url, {params: data.selectedOptions})
        .then(response => {
          data.nextPage === 1 
          ? commit('setItems', response.data)
          : commit('getItems', response.data);
        })
        .catch(err => console.error(err));
    },
    getCount({commit}, data) {
      const url = `/category/${data.category}/items/count`;
      axios
        .get(url, {params: data.selectedOptions})
        .then(response => commit('getTotal', response.data.total))
        .catch(err => console.error(err));
    }
  },
}

export default item;
