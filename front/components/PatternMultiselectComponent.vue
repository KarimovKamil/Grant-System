<template>
  <div class="mt-2">
    <p class="mb-0">{{patternElement.name}}<span class="text-danger" v-if="patternElement.required">*</span></p>
    <multiselect v-model="selected"
                 :options="patternElement.selectableValue"
                 :multiple="true"
                 :close-on-select="false"
                 :clear-on-select="true"
                 placeholder="Выберите несколько"
                 :searchable="true"
                 :optionHeight="40"
                 :showLabels="false"
                 :max-height="150"
                 :limit="3"
    />
  </div>
</template>
<script>
  import Multiselect from 'vue-multiselect';

  export default {
    name: 'pattern-multiselect-component',
    components: {
      Multiselect
    },
    props: {
      patternElement: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        selected: null
      }
    }
  }
</script>

<style lang="less">
  .multiselect--active {
    z-index: 3;

    .multiselect__tags {
      border-bottom-right-radius: 0 !important;
      border-bottom-left-radius: 0 !important;
    }
  }

  .multiselect {
    max-width: 600px;
    min-height: 40px;
    position: relative;

    .multiselect__tags {
      display: block;
      font-size: 1rem;
      border: 1px solid #ced4da;
      border-radius: .25rem;
      padding: .375rem .75rem;

      .multiselect__tags-wrap {
        display: inline-block;

        .multiselect__tag {
          background: #007bff;
          color: #fff;
          padding: 5px;
          border-radius: 5px;
          margin-right: 3px;
          margin-bottom: 5px;
          white-space: nowrap;

          .multiselect__tag-icon {
            width: 22px;
            height: 22px;
          }
        }
      }

      .multiselect__strong {
        display: inline-block;
      }

      .multiselect__input {
        border: none;
        border-radius: 5px;
        display: inline-block;
        min-height: 20px;
        line-height: 20px;
        position: relative !important;
        font-size: 16px;

        &:focus {
          outline: none;
        }
      }
    }

    .multiselect__content-wrapper {
      position: absolute;
      background: #fff;
      overflow: auto;
      border-bottom-left-radius: 5px;
      border-bottom-right-radius: 5px;
      border: 1px solid #6c757d;
      border-top: none;
      width: 100%;

      .multiselect__content {
        list-style: none;
        padding: 0;
        margin: 0;
        min-width: 100%;

        .multiselect__element {
          display: block;

          .multiselect__option {
            display: block;
            padding: 12px;
            min-height: 40px;
            white-space: nowrap;
            vertical-align: middle;
          }

          .multiselect__option--selected {
            background: #007bff;
            color: #fff;
            font-weight: 700;
          }

          .multiselect__option--highlight {
            background: #007bff;
            color: #fff;
            font-weight: 700;
          }
        }
      }
    }
  }
</style>