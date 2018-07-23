<template>
  <main role="main" class="container pt-5">
    <div class="bg-white rounded p-3 shadow mx-auto text-center" style="width: 25rem">
      <h3 class="border-bottom">Войти</h3>
      <div class="alert alert-danger"
           v-if="error !== null">
        {{error.message}}
      </div>
      <div class="mt-3">
        <div class="form-group">
          <label for="loginInput">E-mail</label>
          <input class="form-control"
                 id="loginInput"
                 type="text"
                 v-model="user.email">
          <div class="alert alert-danger mt-2"
               v-if="!validations.emailValid">Неверно введен e-mail
          </div>
        </div>

        <div class="form-group">

          <label for="passwordInput">Пароль</label>
          <input class="form-control"
                 id="passwordInput"
                 type="password"
                 v-model="user.password">
          <div class="alert alert-danger mt-2"
               v-if="!validations.passwordValid">Пароль должен содержать больше 6 и меньше 40 символов
          </div>
        </div>
      </div>

      <div class="pt-2">
        <button class="btn btn-primary w-100"
                @click="logIn"
                :disabled="!validations.emailValid || !validations.passwordValid">Войти
        </button>
      </div>

      <div class="pt-3">
        <a href="/signup">Нет аккаунта</a>
      </div>
    </div>
  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "LogInComponent",
    data() {
      return {
        user: {
          email: "",
          password: ""
        },
        validations: {
          emailValid: true,
          passwordValid: true
        },
        error: null
      }
    },
    methods: {
      logIn() {
        axios.post(`${this.$store.state.globalUrl}/users/login`, this.user)
          .then(response => {
            if (response.status === 200) {
              this.$cookies.set("authToken", response.data.token);
              window.location = "/events"
            } else {
              this.error = response.data;
            }
          })
      }
    },
    watch: {
      "user.email"(newEmail, oldEmail) {
        const regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$/i;
        this.validations.emailValid = newEmail.search(regex) !== -1;
      },
      "user.password"(newPassword, oldPassword) {
        this.validations.passwordValid = newPassword.length >= 6 && newPassword.length <= 40;
      },
    }
  }
</script>
