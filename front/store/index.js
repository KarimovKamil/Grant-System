import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const state = () => ({
  responseAfterRegistration: ''
});

export const mutations = {
  changeResponse(state, response) {
    state.responseAfterRegistration = response;
  }
};