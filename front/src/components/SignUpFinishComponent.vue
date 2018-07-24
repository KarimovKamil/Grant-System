<template>
  <main role="main" class="container">
    <div class="bg-white rounded p-4 shadow mb-5">
      <h4>{{this.$store.state.responseAfterRegistration}}</h4>
    </div>
  </main>
</template>

<script>
  export default {
    name: "SignUpFinishComponent",
    beforeMount() {
      if (this.$store.state.responseAfterRegistration === "") window.location = "/";
    }
  }
</script>

<style scoped>

</style>