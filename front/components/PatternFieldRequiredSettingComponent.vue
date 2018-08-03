<template>
  <div class="d-flex p-1">
    <p class="mb-0 pr-2">Обязательное поле:</p>
    <div class="form-check pr-2">
      <input type="radio" class="form-check-input" v-model="patternElement.required" :value="true">
      <label class="form-check-label">Да</label>
    </div>

    <div class="form-check pr-2">
      <input type="radio" class="form-check-input" v-model="patternElement.required" :value="false">
      <label class="form-check-label">Нет</label>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'PatternFieldRequiredSettingComponent',
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    }
  }
</script>