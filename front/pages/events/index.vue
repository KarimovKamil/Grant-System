<template>
  <main role="main" class="container">
    <div class="d-flex align-items-center justify-content-between p-3 px-4 my-3 text-white bg-dark rounded shadow-box">
      <div class="d-flex flex-row">
        <h4 class="pt-1 mb-0 pb-0 mr-2 text-white lh-100">События</h4>

        <div class="btn-group btn-group-toggle">
          <label :class="['btn', 'btn-secondary', { active: isAll }]" @click="allChosen">
            <input type="radio" id="option1" autocomplete="off" :checked="isAll"/>All
          </label>
          <label :class="['btn', 'btn-secondary', { active: !isAll }]" @click="activeChosen">
            <input type="radio" id="option2" autocomplete="off" :checked="!isAll"/>Active
          </label>
        </div>
      </div>
      <div>
        <a href="/events/new" class="btn btn-light btn-sm">Создать событие</a>
      </div>
    </div>

    <div class="align-items-center p-3 my-3 text-black bg-white rounded shadow-box">
      <div class="wrapper p-3 my-2 border border-dark rounded w-100" v-for="eventItem in events" :key="eventItem.id">
        <a v-bind:href="`/events/${eventItem.id}`" class="text-dark">
          <h4 class="border-bottom border-dark p-0 m-0">{{eventItem.name}}</h4>
          <p class="mt-3">{{eventItem.description}}</p>
          <div class="d-flex flex-row-reverse">
            <p class="mb-0 font-weight-light font-italic">Автор: {{eventItem.owner.firstName}}
              {{eventItem.owner.secondName}}</p>
          </div>
        </a>
      </div>
    </div>
  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "EventsComponent",
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
        axios.get('/api/users/events?from=1&count=50')
          .then(response => this.events = response.data);
      },
      activeChosen() {
        this.isAll = false;
        axios.get('/api/users/events/active?from=1&count=50')
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