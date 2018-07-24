<template>
  <main role="main" class="container">
    <div class="bg-dark rounded p-3 shadow mb-3">
      <h3 class="text-white mb-0">Заявка</h3>
    </div>
    <div class="bg-white text-align-center p-3 shadow"
         v-if="patternExists">
      <h2 class="border-bottom mb-0 border-dark">{{pattern.applicationName}}</h2>
      <div class="pattern-wrapper mt-3">
        <pattern-element-component v-for="element in pattern.elements"
                                   :pattern-element="element"/>
      </div>
    </div>
    <div class="m-5 p-5 text-center"
         v-else>
      <h1 class="text-secondary">Нет шаблона заявки</h1>
    </div>
    <div class="d-flex flex-row-reverse bg-dark p-3 rounded shadow mb-5 mt-3">
      <button class="btn btn-success"
              @click="sendPattern"
              v-if="patternExists">
        Отправить заявку
      </button>
      <a :href="`/events/${this.$route.params.id}/pattern/new`"
         class="btn btn-primary"
         v-else>
        Добавить шаблон
      </a>
    </div>
  </main>
</template>

<script>
  import axios from "axios";
  import PatternElementComponent from "./PatternElementComponent";
  import pattern from "../store/modules/pattern";

  export default {
    name: "PatternComponent",
    components: {
      PatternElementComponent
    },
    data() {
      return {
        pattern: null,
        patternExists: false
      }
    },
    beforeMount() {
      axios.get(`${this.$store.state.globalUrl}/users/events/${this.$route.params.id}/pattern`)
        .then(response => {
          if (response.status === 200) {
            this.pattern = response.data;
            this.patternExists = true;
          } else {
            this.patternExists = false;
          }
        });
    },
    methods: {
      sendPattern() {
        let application = {
          patternId: this.pattern.id,
          values: []
        };
        this.pattern.elements.forEach(element => {
          application.values.push({elementId: element.id, filledValue: element.filledValue});
        });
        let required = this.pattern.elements
          .filter(element => element.required)
          .reduce((acc, element) => acc && application.values.find(value => value.elementId === element.id).filledValue !== undefined, true);
        if (required) {
          axios.post(
            `${this.$store.state.globalUrl}/users/application`,
            application,
            {headers: {"Auth-Token": this.$cookies.get("authToken")}}
          ).then(response => window.location = '/events');
        }
      }
    }
  }
</script>

<style scoped>

</style>