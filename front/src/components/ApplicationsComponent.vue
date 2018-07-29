<template>
  <main role="main" class="container pb-5">
    <div class="bg-dark text-white p-3 rounded shadow mb-3">
      <h3 class="mb-0">Заявки</h3>
    </div>

    <div v-if="applications.length !== 0" class="bg-white p-3 rounded shadow">
      <a :href="`/applications/${application.id}`" v-for="application in applications" class="text-dark">
        <div class="p-3 my-2 border border-dark rounded w-100">
          <h5 class="mb-3">Название заявки: {{application.applicationName}}</h5>
          <p class="mb-1">Статус: {{application.status}}</p>
          <p class="mb-0">Дата: {{new Date(application.applicationDate).toLocaleString()}}</p>
        </div>
      </a>
    </div>
  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "ApplicationsComponent",
    data() {
      return {
        applications: []
      }
    },
    beforeMount() {
      axios.get(`${this.$store.state.globalUrl}/users/applications?from=0&count=50`, {headers: {"Auth-Token": this.$cookies.get("authToken")}})
        .then(response => {
          if (response.status === 200) {
            this.applications = response.data;
          }
        })
    }
  }
</script>

<style lang="less" scoped>
  a:hover {
    text-decoration: none;
  }
</style>