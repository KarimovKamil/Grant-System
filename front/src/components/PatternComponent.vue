<template>
  <main role="main" class="container">
    <div class="bg-white text-align-center p-3 shadow mb-4">
      <h2 class="border-bottom mb-0 border-dark">{{pattern.applicationName}}</h2>
      <div class="pattern-wrapper mt-3">
        <pattern-element-component v-for="element in pattern.elements" v-bind:pattern-element="element" />
      </div>
    </div>
  </main>
</template>

<script>
  import axios from "axios";
  import PatternElementComponent from "./PatternElementComponent";

  export default {
    name: "PatternComponent",
    components: {
      PatternElementComponent
    },
    data() {
      return {
        pattern: null
      }
    },
    beforeMount() {
      axios.get(`${this.$store.state.globalUrl}/users/events/${this.$route.params.id}/pattern`)
        .then(response => this.pattern = response.data);
    }
  }
</script>

<style scoped>

</style>