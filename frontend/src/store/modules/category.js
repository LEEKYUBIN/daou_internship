import axios from 'axios';

const category = {
  namespaced: true,
  state: {
    categories: [],
    loading: true,
  },
  mutations: {
    fetchCategory(state, data) {
      state.categories = data;
    },
    changeLoading(state, flag) {
      state.loading = flag;
    },
  },
  actions: {
    fetchCategory({commit}) {
      const url = `/categories/`;
      axios
        .get(url)
        .then(response => commit('fetchCategory', response.data))
        .then(() => commit('changeLoading', false))
        .catch(err => console.error(err));
    },
  }
}

export default category;