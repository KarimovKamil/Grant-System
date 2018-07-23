<template>
  <main role="main" class="container">
    <div class="d-flex align-items-center justify-content-between p-3 px-4 my-3 text-white bg-dark rounded shadow-box">
      <div class="d-flex flex-row">
        <h4 class="pt-1 mb-0 pb-0 mr-2 text-white lh-100">События</h4>

        <div class="btn-group btn-group-toggle">
          <label v-bind:class="['btn', 'btn-secondary', { active: isAll }]" v-on:click="allChosen">
            <input type="radio" id="option1" autocomplete="off" v-bind:checked="isAll"/>All
          </label>
          <label v-bind:class="['btn', 'btn-secondary', { active: !isAll }]" v-on:click="activeChosen">
            <input type="radio" id="option2" autocomplete="off" v-bind:checked="!isAll"/>Active
          </label>
        </div>
      </div>
      <div>
        <a href="/events/new" class="btn btn-light btn-sm">Создать событие</a>
      </div>
    </div>

    <div class="align-items-center p-3 my-3 text-black bg-white rounded shadow-box">
      <event-item v-for="eventItem in events" v-bind:event="eventItem"/>
    </div>
  </main>
</template>

<script>
  import EventItem from "./EventItem";
  import axios from "axios";

  export default {
    name: "EventsComponent",
    components: {EventItem},
    data() {
      return {
        isAll: true,
        events: []
      }
    },
    beforeMount() {
      this.allChosen();
    },
    methods: {
      allChosen() {
        this.isAll = true;
        axios.get(`${this.$store.state.globalUrl}/users/events?from=1&count=50`)
          .then(response => this.events = response.data);
      },
      activeChosen() {
        this.isAll = false;
        axios.get(`${this.$store.state.globalUrl}/users/events/active?from=1&count=50`)
          .then(response => this.events = response.data);
      }
    }
  }
</script>

<style lang="less" scoped>
  .events-header {
    background: #ff0000;
  }

  .events-list {
    background-color: #ff0000;
  }
</style>