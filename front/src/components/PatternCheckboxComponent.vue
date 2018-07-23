<template>
  <div class="form-group m-2">
    <p class="mb-1">{{patternElement.name}}<span class="text-danger" v-if="patternElement.required">*</span></p>
    <div class="form-check ml-2" v-for="value in patternElement.selectableValue">
      <input class="form-check-input" type="checkbox" v-model="selected" :value="value">
      <label class="form-check-label ml-2 mb-1">{{value}}</label>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'pattern-checkbox-component',
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        selected: []
      }
    }
  }
</script>