<template>
  <div id="products">
    <Loading v-if="$store.state.category.loading" ></Loading>
    <ul class="products_items_link">
      <li 
        v-for="category in $store.state.category.categories" 
        :key="category.no"
        class="products_item" 
      >
        <router-link 
          class="products_link" 
          :to="{ name: 'Items', params: {category: category.text}}" 
        >
          <img 
            :src="category.img" 
            alt=""
            class="products_img"  
          >
          <span class="products_name">{{category.name}}</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import '../../assets/css/Products/Products.css';
import Loading from '../Loading/Loading';
import store from '../../store/store';

export default {
  name: 'Products',
  components: {
    Loading,
  },
  mounted() {
    store.commit['category/changeLoading', true];
  },
}
</script>

<style scoped>
</style>