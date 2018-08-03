<template>
  <main role="main" class="container pb-5" v-if="application !== null">
    <div class="bg-dark text-white p-3 rounded shadow mb-3">
      <h3 class="mb-0">Просмотр заявки</h3>
    </div>

    <div class="bg-white p-4 rounded shadow mb-3">
      <h5>Название: {{application.applicationName}}</h5>
      <p>Статус: {{application.status}}</p>
      <p v-for="value in application.values">
        {{value.element.name}}: {{value.filledValue}}
      </p>
      <p v-if="application.comment !== null">{{application.comment}}</p>
    </div>

    <div class="bg-dark text-white p-3 rounded shadow d-flex flex-row-reverse">
      <button class="btn btn-danger" @click="deleteApplication">Удалить заявку</button>
    </div>

  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "ApplicationComponent",
    data() {
      return {
        application: null
      }
    },
    beforeMount() {
      axios.get(`/api/users/applications/${this.$route.params.id}`, {headers: {"Auth-Token": this.$cookies.get("authToken")}})
        .then(response => {
          if (response.status === 200) {
            this.application = response.data;
          } else if (response.status === 400) {
            this.$router.push("/applications");
          }
        })
    },
    methods: {
      deleteApplication() {
        axios.delete(`/api/users/applications/${this.$route.params.id}`, {headers: {"Auth-Token": this.$cookies.get("authToken")}})
          .then(response => {
            if (response.status === 200) {
              this.$router.push("/applications");
            }
          });
      }
    }
  }
</script>