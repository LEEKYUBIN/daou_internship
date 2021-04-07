import axios from 'axios';

const option = {
  namespaced: true,
  state: {
    options: [],
  },
  mutations: {
    getOption(state, data) {
      state.options = data;
    },
  },
  actions: {
    getOption({commit}, category) {
      const url = `/category/${category}/filter/`;
      axios
        .get(url)
        .then(response => commit('getOption', response.data))
        .catch(err => console.error(err));
    },
  }
}

export default option;