<template>
  <div>
    <app-header/>
    <router-view/>
  </div>
</template>

<script>
  import AppHeader from './components/AppHeader';

  export default {
    name: 'app',
    components: {AppHeader}
  }
</script>

<style>
  body {
    margin-top: 66px;
  }
</style>
