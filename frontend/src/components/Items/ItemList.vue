<template>
  <div class="item_list">
    <div class="total_cnt">총 {{ totalItem }}개</div>
    <div class="item_list_sorting">
      <input 
        type="button" 
        value="낮은가격순" 
        class="item_list_sorting_btn"
        @click="changeSorting"
      > |
      <input 
        type="button" 
        value="높은가격순" 
        class="item_list_sorting_btn"
        @click="changeSorting"
      > | 
      <input 
        type="button" 
        value="리뷰순" 
        class="item_list_sorting_btn"
        @click="changeSorting"
      >
    </div>
    <div class="item_list_items">
      <div 
        v-for="item in items" 
        :key="item.no" 
        class="item_list_item" 
      >
        <!-- 카트에 있으면 -->
        <i 
          v-if="~cartId.indexOf(item.no)"
          class="fas fa-heart item_list_heart"
          @click="addToCart(item)"
        ></i>
        <!-- 카트에 없으면 -->
        <i 
          class="far fa-heart item_list_heart"
          @click="addToCart(item) "
        ></i>
        <div 
          class="item_list_item_img" 
          :style="{ backgroundImage: `url(${item.img})` }"
        ></div>
        <div class="item_list_item_info">
          <div class="item_list_item_name">{{ item.name }}</div>
          <div class="item_list_item_price">{{ item.price }}원</div>
          <div class="item_list_item_review">
            <span class="item_list_review_rate">{{ item.review }}점</span>  
            <span>({{ item.reviewNum }})</span>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import '../../assets/css/Items/ItemList.css';
import store from '../../store/store';

export default {
  name: 'ItemList',
  computed: {
    totalItem() {
      return store.state.item.totalItem;
    },
    items() {
      return store.state.item.items;
    },
    cartId() {
      return store.state.information.cartId;
    }
  }, 
  methods: {
    changeSorting(event) {
      const command = event.target.value;
      this.items.sort((item1,item2) => {
        if (command === '낮은가격순') {
          return item1.price - item2.price;
        } else if (command === '높은가격순') {
          return item2.price - item1.price;
        } else if (command === '리뷰순') {
          return item2.reviewNum - item1.reviewNum;
        }
      });
    },
    addToCart(item) {
      const cntCart = store.getters['information/cntCart'];
      if (this.cartId.indexOf(item.no) !== -1) {
        alert('동일한 품목이 이미 리스트에 존재합니다.');
      } else if (cntCart >= 3) {
        alert('최대 3개까지 선택가능합니다.');
      } else {
        store.dispatch('information/addToCart', item);
      }
    },
    deleteFromCart(item) {
      store.dispatch('information/deleteFromCart', item);
    },
  },
}
</script>

<style scoped>
</style>