<template>
  <div class="form-group m-2">
    <label>{{patternElement.name}}<span class="text-danger" v-if="patternElement.required">*</span></label>
    <input class="form-control ml-2" v-model="patternElement.filledValue" type="text" placeholder="Введите...">
  </div>
</template>
<script>
  export default {
    name: 'pattern-text-component',
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        selected: ""
      }
    }
  }
</script>
