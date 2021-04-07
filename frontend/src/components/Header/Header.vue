<template>
  <div 
    id="header" 
    class="nav"
  >
    <ul class="nav_items_link">
      <li>
        <router-link 
          :to="{ name: 'Products' }" 
          class="nav_link"
        >
          <i class="fas fa-home"></i>
        </router-link>
      </li>
    </ul>
    
    <ul class="nav_items_link">
      <li 
        v-for="category in $store.state.category.categories" 
        :key="category.no"
      >
        <router-link 
          :to="{ name: 'Items', params: { category: category.text }}" 
          class="nav_link"
        >
          {{category.name}}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import '../../assets/css/Header/Header.css';

export default {
  name: 'Header',
}
</script>

<style scoped>
</style>