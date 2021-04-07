<template>
  <div id="app">
    <Header></Header>
    <!-- 같은 페이지에서 params 값만 갱신하여 router 이동 -->
    <router-view :key="$route.fullPath" />
  </div>
</template>

<script>
import Header from './components/Header/Header';

export default {
  name: 'App',
  components: {Header},
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
