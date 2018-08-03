<template>
  <div class="p-1">
    <pattern-field-name-setting-component :pattern-element="patternElement"/>

    <pattern-field-required-setting-component :pattern-element="patternElement"/>

    <pattern-field-values-setting-component v-if="patternElement.type !== 'TEXT'"
                                            :pattern-element="patternElement"/>
  </div>
</template>

<script>
  import PatternFieldNameSettingComponent from "./PatternFieldNameSettingComponent";
  import PatternFieldRequiredSettingComponent from "./PatternFieldRequiredSettingComponent";
  import PatternFieldValuesSettingComponent from "./PatternFieldValuesSettingComponent";

  export default {
    name: "PatternElementSettingsComponent",
    components: {
      PatternFieldNameSettingComponent,
      PatternFieldRequiredSettingComponent,
      PatternFieldValuesSettingComponent
    },
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    }
  }
</script>

