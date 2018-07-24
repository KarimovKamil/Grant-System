<template>
  <main role="main" class="container">
    <div class="bg-white shadow rounded p-3 text-align-center mb-4">
      <h3 class="border-bottom border-dark pl-3 pt-2">{{event.name}}</h3>
      <p class="px-2 mt-3">{{event.description}}</p>
      <div class="d-flex flex-row-reverse px-3">
        <p class="mb-0 font-italic">Автор: {{event.owner.firstName}} {{event.owner.secondName}}</p>
      </div>
    </div>
    <div class="d-flex flex-row-reverse bg-dark p-3 rounded shadow">
      <a :href="`/events/${event.id}/pattern`" class="btn btn-primary mr-2">Заполнить заявку</a>
    </div>
  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "EventComponent",
    data() {
      return {
        event: null
      }
    },
    beforeMount() {
      axios.get(`${this.$store.state.globalUrl}/users/events/${this.$route.params.id}`)
        .then(response => this.event = response.data);
    }
  }
</script>
