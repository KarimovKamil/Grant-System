<template>
  <div class="form-group p-1">
    <label>
      Название:
      <input type="text" class="form-control" v-model="patternElement.name">
    </label>
  </div>
</template>
<script>
  export default {
    name: 'PatternFieldNameSettingComponent',
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    }
  }
</script>
