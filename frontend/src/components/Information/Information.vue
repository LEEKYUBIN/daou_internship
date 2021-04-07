<template>
  <div id="information">
    <div class="information_container">
      <!-- 사이드바 -->
      <div class="information_items">
        <div 
          class="information_item" 
        >
          <div 
            class="information_item_img"
          ></div>
          <div 
            v-for="(attributeDetail, attribute) in cartAttribute"
            v-show="attribute !== 'img' && attribute !== 'no' && attribute !== 'review'"
            :key="attribute"
            class="information_item_attribute information_item_sidebar"
          >{{ attributeDetail }}</div>
          <div 
            v-for="(attributeObj, index) in itemInformationArr[0]"
            :key="index"
            class="information_item_attribute information_item_sidebar"
          >
            {{attributeObj.attribute}}
          </div>
        </div>
      </div>

      <!-- 상품들 -->
      <div class="information_items">
        <div 
          v-for="(cartItem, cartIndex) in cartItems"
          :key="cartItem.no"
          class="information_item" 
        >
          <div 
            :style="{ backgroundImage: `url(${cartItem.img})` }"
            class="information_item_img"
          >
          </div>
          <div 
            v-for="(attributeDetail, attribute) in cartItem"
            v-show="attribute !== 'img' && attribute !== 'no' && attribute !== 'review'"
            :key="attribute"
            class="information_item_attribute"
          >{{ attributeDetail }}</div>
          <div 
            v-for="(attributeObj, index) in itemInformationArr[cartIndex]"
            :key="index"
            class="information_item_attribute"
          >
            {{attributeObj.attributeDetail}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../../assets/css/Information/Information.css';
import store from '../../store/store';

export default {
  name: 'Information',
  data() {
    return {
      cartAttribute: {
        'img': '사진',
        'name': '모델명',
        'no': '번호',
        'price': '가격',
        'review': '평점',
        'reviewNum': '리뷰 수',
      },
    };
  },
  computed: {
    cartItems() {
      return store.state.information.cart;
    },
    itemInformationArr() {
      return store.state.information.informations;    
    }
  }
}
</script>

<style scoped>

</style>