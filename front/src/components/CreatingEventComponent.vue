<template>
  <main role="main" class="container">
    <div class="bg-white p-3 rounded shadow mb-5">
      <div class="border-bottom pl-3">
        <h3 class="mb-0">Создание события</h3>
      </div>

      <div class="p-2">
        <div class="form-group">
          <label for="newEventName">Название</label>
          <input class="form-control" type="text" id="newEventName" v-model="newEvent.name">
        </div>

        <div class="form-group">
          <label for="newEventSiteUrl">Ссылка на сайт</label>
          <input class="form-control" type="text" id="newEventSiteUrl" v-model="newEvent.siteUrl">
        </div>

        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="newEventStartDate">Дата начала</label>
            <input class="form-control"
                   type="datetime-local"
                   id="newEventStartDate"
                   v-model="newEvent.startDate">
          </div>
          <div class="form-group col-md-6">
            <label for="newEventEndDate">Дата окончания</label>
            <input class="form-control"
                   type="datetime-local"
                   id="newEventEndDate"
                   v-model="newEvent.endDate">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label for="newEventDescription">Описание</label>
        <textarea class="form-control"
                  id="newEventDescription"
                  rows="8"
                  wrap="soft"
                  v-model="newEvent.description"></textarea>
      </div>

      <div class="text-center">
        <button class="btn btn-primary"
                @click="createEvent">Создать событие</button>
      </div>
    </div>
  </main>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "CreatingEventComponent",
    data() {
      return {
        newEvent: {
          description: "",
          endDate: "",
          name: "",
          siteUrl: "",
          startDate: ""
        }
      }
    },
    methods: {
      createEvent() {
        axios.post(
          `${this.$store.state.globalUrl}/organizers/events`,
          this.newEvent,
          {headers: {"Auth-Token": this.$cookies.get("authToken")}})
          .then(response => window.location = `/events/${response.data.id}`);
      }
    }
  }
</script>

<style lang="less" scoped>
  textarea {
    resize: none;
  }
</style>