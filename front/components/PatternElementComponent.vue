<template>
  <pattern-text-component v-if="patternElement.type === 'TEXT'" v-bind:pattern-element="patternElement"/>

  <pattern-combobox-component v-else-if="patternElement.type === 'COMBOBOX'" v-bind:pattern-element="patternElement"/>

  <pattern-checkbox-component v-else-if="patternElement.type === 'CHECKBOX'" v-bind:pattern-element="patternElement"/>

  <pattern-radiobutton-component v-else-if="patternElement.type === 'RADIOBUTTON'" v-bind:pattern-element="patternElement"/>

  <!--<pattern-multiselect-component v-else-if="patternElement.type === 'MULTISELECT'" v-bind:pattern-element="patternElement"/>-->
</template>

<script>
  import PatternTextComponent from "./PatternTextComponent";
  import PatternComboboxComponent from "./PatternComboboxComponent";
  import PatternCheckboxComponent from "./PatternCheckboxComponent";
  import PatternRadiobuttonComponent from "./PatternRadiobuttonComponent";
  import PatternMultiselectComponent from "./PatternMultiselectComponent";

  export default {
    name: "PatternElementComponent",
    components: {
      PatternMultiselectComponent,
      PatternRadiobuttonComponent, PatternCheckboxComponent, PatternComboboxComponent, PatternTextComponent},
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        selectedValues: [

        ]
      }
    }
  }
</script>

