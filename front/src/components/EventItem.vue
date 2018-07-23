<template>
  <div class="wrapper p-3 my-2 border border-dark rounded w-100">
    <a v-bind:href="`/events/${event.id}`" class="text-dark">
      <h4 class="border-bottom border-dark p-0 m-0">{{event.name}}</h4>
      <p class="mt-3">{{event.description}}</p>
      <div class="d-flex flex-row-reverse">
        <p class="mb-0 font-weight-light font-italic">Автор: {{event.owner.firstName}} {{event.owner.secondName}}</p>
      </div>
    </a>
  </div>
</template>

<script>
  export default {
    name: "EventItem",
    props: {
      event: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style lang="less" scoped>
  a:hover {
    text-decoration: none;
  }
</style>