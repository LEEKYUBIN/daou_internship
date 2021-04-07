import axios from 'axios';

const information = {
  namespaced: true,
  state: {
    cartId: [],
    cart: [],
    informations: [],
  },
  getters: {
    cntCart(state) {
      return state.cart.length;
    },
  },
  mutations: {
    addToCart(state, data) {
      state.cart = state.cart.concat(data)
      state.cartId.push(data.no);
    },
    deleteFromCart(state, data) {
      const itemIndex = state.cartId.indexOf(data.no);
      state.cart = state.cart.filter(item => item.no !== data.no);
      state.cartId.splice(itemIndex, 1);
    },
    getInformation(state, data) {
      state.informations = data;
    }
  },
  actions: {
    addToCart({commit}, item) {
      commit('addToCart', item);
    },
    deleteFromCart({commit}, item) {
      commit('deleteFromCart', item);
    },
    getInformation({commit}, data) {
      const url = `/information`;
      axios
        .get(url, {params: data.cart})
        .then(response => {
          commit('getInformation', response.data);
        })
        .catch(err => console.error(err));
    }
  },
}

export default information;
