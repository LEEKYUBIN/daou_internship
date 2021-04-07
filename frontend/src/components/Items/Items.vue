<template>
  <div id="items">
    <div class="items_container">
      <div class="items_cover_img"></div>
      <Option @getItems="getItems"></Option>
      <ItemList></ItemList>
      <div class="items_scroll">
        <i class="fas fa-angle-double-down items_scroll_icon"></i>
        <span class="items_scroll_comments">{{ scrollComment }}</span>
      </div>   
    </div>
    <Cart></Cart>
    <div
      class="items_top_scroll"
      @click="scrollToTop()" 
    >
      <i class="fas fa-arrow-up items_top_scroll_icon"></i>
    </div>
  </div>
</template>

<script>
import Option from './Option.vue';
import ItemList from './ItemList.vue';
import Cart from './Cart.vue';
import store from '../../store/store';

import '../../assets/css/Items/Items.css';

export default {
	name: 'Items',
  components: {
    Option,
    ItemList,
    Cart,
  },
  data() {
    return {
      selectedOptions: {},
    }
  },
  computed: {
    scrollComment() {
      const totalPage = store.getters['item/totalPage'];
      const currentPage = store.getters['item/currentPage']
      return currentPage === totalPage ? '마지막 항목입니다.' : '스크롤를 끝까지 내려주세요.';
    },
    category() {
      return this.$route.params.category;
    }
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },
  methods: {
    scrollToTop() {
      document.getElementById('app').scrollIntoView({behavior: "smooth"});
    },
    getItems(selectedOptions) {
      this.selectedOptions = selectedOptions;
      store.dispatch('item/getItems', {
        category: this.$route.params.category,
        nextPage: 1,
        selectedOptions: selectedOptions,
      });
      store.dispatch('item/getCount', {
        category: this.$route.params.category,
        selectedOptions: selectedOptions,
      });
    },
    onScroll() {
      const currentScrollY = window.scrollY;
      const scrollHeight = document.documentElement.clientHeight;
      const viewHeight = document.documentElement.scrollHeight;
      if (currentScrollY + scrollHeight === viewHeight) {
        const totalPage = store.getters['item/totalPage'];
        const cntItems = store.getters['item/cntItems'];
        const currentPage = store.getters['item/currentPage'];
        if (currentPage === totalPage) return
        const nextPage = cntItems ? currentPage + 1 : 1;
        const changeSelectedOptions = this.selectedOptions;
        store.dispatch('item/getItems', {
          category: this.$route.params.category,
          nextPage: nextPage,
          selectedOptions: changeSelectedOptions,
        });
      }
    },
  }
}
</script>

<style scoped>
</style>