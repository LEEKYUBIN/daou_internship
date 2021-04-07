<template>
  <div class="cart">
    <div class="cart_header">
      비교상품목록
      <i class="fas fa-caret-right"></i>
    </div>
    
    <div class="cart_body">
      <div 
        v-for="cartItem in $store.state.information.cart" 
        :key="cartItem.no" 
        class="cart_body_img" 
        :style="{backgroundImage: `url(${cartItem.img})`}"
      >
        <i 
          class="fas fa-times-circle delete_cart" 
          @click="deleteFromCart(cartItem)"
        ></i>
      </div>
    </div>
    
    <div class="cart_btn" @click="getInformationOfCart()">
      <router-link 
        :to="{ name: 'Information' }" 
        class="link"
      >
        <span class="cart_link_name">{{compareName}}</span>
      </router-link>
    </div>
  </div>
</template>

<script>
import '../../assets/css/Items/Cart.css';
import store from '../../store/store';

export default {
  name: 'Cart',
  computed: {
    compareName() {
      const cntCart = store.getters['information/cntCart'];
      if (cntCart == 0 ) return '카트 담기'
      else if (cntCart == 1) return '상세 보기';
      else return '비교하기';
    },
  },
  methods: {
    deleteFromCart(item) {
      store.dispatch('information/deleteFromCart', item);
    },
    getInformationOfCart() {
      const cntCart = store.getters['information/cntCart'];
      const cart = store.state.information.cartId;
      const changeParams = () => {
        const params = new URLSearchParams();
        cart.forEach(itemId => {
          params.append('items', itemId);
        });
        return params;
      }
      if (cntCart > 0) {
        store.dispatch('information/getInformation', {
          cart: changeParams(),
        })
      } else {
        alert('최소 1개 이상 선택하세요.');   
      }
    },
  },
}
</script>

<style scoped>
</style>