<template>
  <main role="main" class="container">
    <div class="bg-white rounded p-4 shadow mb-5">
      <!--<h4>{{this.$store.state.responseAfterRegistration}}</h4>-->
      <h4>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet architecto consectetur cum cumque ea et ex id incidunt labore laborum maiores minus nostrum perferendis, quo ratione sapiente similique vel veritatis?</h4>
    </div>
  </main>
</template>

<script>
  export default {
    name: "SignUpFinishComponent",
    beforeMount() {
      if (this.$store.state.responseAfterRegistration === "") window.location("/");
    }
  }
</script>

<style scoped>

</style>