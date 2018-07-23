<template>
  <div class="p-1">
    <div class="d-flex flex-wrap">
      <p class="mb-0 mt-1 mr-2 align-middle">Варианты:</p>
      <div class="bg-primary rounded text-white mr-1 mb-1 d-flex" v-for="value in patternElement.selectableValue">
        <span class="py-1 pl-1">{{value}}</span>
        <button class="btn btn-sm btn-primary h-auto ml-1 px-2 py-0" @click="removeValue">X</button>
      </div>
    </div>
    <div class="form-group pt-2">
      <label>Добавить вариант</label>
      <div class="d-flex">
        <input class="form-control mx-1" type="text" v-model="newValue">
        <button class="btn btn-primary mx-1" @click="addValue">Добавить</button>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'PatternFieldValuesSettingComponent',
    props: {
      patternElement: {
        type: Object,
        required: true
      },
    },
    data() {
      return {
        newValue: ""
      }
    },
    methods: {
      addValue() {
        if (this.patternElement.selectableValue.findIndex(value => value === this.newValue) === -1) {
          this.patternElement.selectableValue.push(this.newValue);
        }
        this.newValue = "";
      },
      removeValue(event) {
        const removingValue = event.target.parentElement.children[0].innerHTML;
        this.patternElement.selectableValue = this.patternElement.selectableValue.filter(value => value !== removingValue);
      }
    }
  }
</script>