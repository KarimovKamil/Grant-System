<template>
  <div class="form-group m-2">
    <label class="mb-0">{{patternElement.name}}<span class="text-danger" v-if="patternElement.required">*</span></label>
    <multiselect v-model="selected"
                 :options="patternElement.selectableValue"
                 :close-on-select="true"
                 placeholder="Выберите..."
                 :searchable="true"
                 :optionHeight="40"
                 :showLabels="false"
                 :max-height="150"
    />
  </div>
</template>
<script>
  import Multiselect from 'vue-multiselect';

  export default {
    name: 'pattern-combobox-component',
    components: {
      Multiselect
    },
    data() {
      return {
        selected: null
      }
    },
    props: {
      patternElement: {}
    }
  }
</script>