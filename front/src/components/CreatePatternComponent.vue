<template>
  <main role="main" class="container">
    <div class="bg-dark p-2 rounded-top shadow">
      <h3 class="text-white pl-3 mt-2">Создать шаблон</h3>
    </div>

    <div class="bg-white p-2 rounded-bottom shadow">
      <div class="form-inline">
        <label class="mr-2 mb-0 w-auto flex-grow-1">Название приложения: </label>
        <input class="form-control" type="text" v-model="pattern.applicationName">
      </div>
    </div>

    <div class="m-5 text-center" v-if="pattern.elements.length === 0">
      <h1 class="text-secondary">Нужно добавить элементы</h1>
    </div>

    <div class="fields-wrapper">
      <div :id="`field${index}`" v-for="(field, index) in pattern.elements"
           class="bg-white p-2 mt-3 rounded shadow d-flex p-2">
        <div class="flex-fill w-50 p-2">
          <pattern-element-component :pattern-element="field"/>
        </div>
        <div class="flex-fill w-50 p-2 border-left border-primary">
          <pattern-element-settings-component :pattern-element="field"/>
        </div>
        <div class="flex-fill">
          <i class="material-icons clear-icon" @click="removeField">
            clear
          </i>
        </div>
      </div>
    </div>

    <div class="d-flex flex-row-reverse bg-dark rounded p-2 mt-3 mb-5 shadow">

      <div class="text-center p-2">
        <button class="btn btn-success" @click="createPattern">Создать шаблон</button>
      </div>

      <div class="text-center p-2">
        <div class="dropdown">
          <button class="btn btn-primary dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown"
                  aria-haspopup="true" area-expanded="false">
            Добавить поле
          </button>

          <div class="dropdown-menu" aria-labelledby="dropbdownMenuButton">
            <button class="dropdown-item" @click="addTextField">Добавить текстовое поле</button>
            <button class="dropdown-item" @click="addComboBoxField">Добавить поле ComboBox</button>
            <button class="dropdown-item" @click="addCheckboxesField">Добавить поле Checkbox</button>
            <button class="dropdown-item" @click="addRadioButtonsField">Добавить поле RadioButtons</button>
            <button class="dropdown-item" @click="addMultiSelectField">Добавить поле MultiSelect</button>
          </div>
        </div>
      </div>

    </div>
  </main>
</template>

<script>
  import axios from 'axios';
  import PatternElementComponent from './PatternElementComponent';
  import PatternElementSettingsComponent from './PatternElementSettingsComponent';

  export default {
    name: "CreatePatternComponent",
    components: {
      PatternElementComponent,
      PatternElementSettingsComponent
    },
    data() {
      return {
        pattern: {
          applicationName: "",
          elements: [],
        },
      }
    },
    methods: {
      addTextField() {
        return this.pattern.elements.push(this.$store.getters["pattern/getPatternTextElement"]);
      },
      addComboBoxField() {
        return this.pattern.elements.push(this.$store.getters["pattern/getPatternComboboxElement"]);
      },
      addCheckboxesField() {
        return this.pattern.elements.push(this.$store.getters["pattern/getPatternCheckboxElement"]);
      },
      addRadioButtonsField() {
        return this.pattern.elements.push(this.$store.getters["pattern/getPatternRadioButtonsElement"]);
      },
      addMultiSelectField() {
        return this.pattern.elements.push(this.$store.getters["pattern/getPatternMultiSelectElement"]);
      },
      createPattern() {
        axios.post(
          `${this.$store.state.globalUrl}/organizers/events/${this.$route.params.id}/pattern`,
          this.pattern,
          {headers: {"Auth-Token": this.$cookies.get("authToken")}}
        ).then(response => window.location = `/events/${response.data.event.id}/pattern`);
      },
      removeField(event) {
        let fieldIndex = Number.parseInt(event.target.parentElement.parentElement.id.replace('field', ''));
        this.pattern.elements.splice(fieldIndex, 1)
      }
    }
  }
</script>

<style lang="less" scoped>
  .clear-icon {
    transition: ease 0.3s;
    cursor: pointer;

    &:hover {
      color: #dc3545;
    }
  }
</style>