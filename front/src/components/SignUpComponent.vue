<template>
  <main role="main" class="container">
    <div class="bg-white rounded shadow p-3 mx-auto text-center mb-5" style="width: 40rem">
      <h3 class="border-bottom">Регистрация</h3>

      <div class="mt-3">
        <div class="form-group">
          <label for="secondNameInput">Фамилия</label>
          <input class="form-control"
                 id="secondNameInput"
                 type="text"
                 v-model="user.secondName">
        </div>

        <div class="form-group">
          <label for="firstNameInput">Имя</label>
          <input class="form-control"
                 id="firstNameInput"
                 type="text"
                 v-model="user.firstName">
        </div>

        <div class="form-group">
          <label for="middleNameInput">Отчетсво</label>
          <input class="form-control"
                 id="middleNameInput"
                 type="text"
                 v-model="user.middleName">
        </div>

        <div class="mb-3">
          <p class="mb-2">Дата рождения</p>

          <div class="d-flex">
            <div class="input-group">
              <label class="sr-only" for="birthDayInput">День</label>
              <select class="form-control mr-2"
                      id="birthDayInput"
                      v-model="birthDate.day">
                <option v-for="day in date.daysInMonth"
                        :value="day">{{day}}
                </option>
              </select>
            </div>

            <div class="input-group">
              <label class="sr-only" for="birthMonthInput">Месяц</label>
              <select class="form-control"
                      id="birthMonthInput"
                      v-model="birthDate.month">
                <option value="01">Январь</option>
                <option value="02">Февраль</option>
                <option value="03">Март</option>
                <option value="04">Апрель</option>
                <option value="05">Май</option>
                <option value="06">Июнь</option>
                <option value="07">Июль</option>
                <option value="08">Август</option>
                <option value="09">Сентябрь</option>
                <option value="10">Октябрь</option>
                <option value="11">Ноябрь</option>
                <option value="12">Декабрь</option>
              </select>
            </div>

            <div class="input-group">
              <label class="sr-only" for="birthYearInput">Год</label>
              <input class="form-control ml-2"
                     id="birthYearInput"
                     type="number"
                     min="1900"
                     placeholder="Год"
                     v-model="birthDate.year">
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="emailInput">E-mail</label>
          <input class="form-control"
                 id="emailInput"
                 type="email"
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

        <div class="form-group">
          <label for="passwordConfirmationInput">Повторите пароль</label>
          <input class="form-control"
                 id="passwordConfirmationInput"
                 type="password"
                 v-model="passwordConfirmation">
          <div class="alert alert-danger mt-2"
               v-if="!validations.confirmationPasswordValid">Пароль должен совпадать
          </div>
        </div>
      </div>

      <div class="pt-3">
        <button class="btn btn-primary w-100"
                @click="registerUser"
                :disabled="!validations.emailValid || !validations.passwordValid || !validations.confirmationPasswordValid">
          Зарегистрироваться
        </button>
      </div>

      <div class="pt-3">
        <a href="/">Уже есть аккаунт</a>
      </div>
    </div>
  </main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "SignUpComponent",
    data() {
      return {
        user: {
          email: "",
          firstName: "",
          middleName: "",
          password: "",
          secondName: ""
        },
        passwordConfirmation: "",
        birthDate: {
          day: "",
          month: "",
          year: ""
        },
        date: {
          daysInMonth: [],
        },
        validations: {
          emailValid: true,
          passwordValid: true,
          confirmationPasswordValid: true
        }
      }
    },
    beforeMount() {
      let date = new Date();
      this.birthDate.day = date.getDate().toString();
      this.birthDate.month = (date.getMonth() + 1).toString();
      this.birthDate.year = date.getFullYear().toString();
      if (this.birthDate.month.length === 1) this.birthDate.month = `0${this.birthDate.month}`;
      this.uploadDaysList();
    },
    methods: {
      registerUser() {
        this.user.birthDate = `${this.birthDate.year}-${this.birthDate.month}-${this.birthDate.day}`
        axios.post(
          `${this.$store.state.globalUrl}/users/registration`,
          this.user
        ).then(response => this.$store.commit('changeResponse', response.data.message))
          .then(response => window.location = "/signup/finish")
      },
      uploadDaysList() {
        this.date.daysInMonth = [];

        for (let i = 1; i <= 28; i++) this.date.daysInMonth.push(i.toString());

        if (["01", "03", "05", "07", "08", "10", "12"].findIndex(value => this.birthDate.month === value) !== -1) {
          this.date.daysInMonth.push("29", "30", "31");
        } else if (this.birthDate.month !== "02") {
          this.date.daysInMonth.push("29", "30");
        } else if (new Date(`${this.birthDate.year}-02-29`).getDate() === 29) {
          this.date.daysInMonth.push("29")
        }
      }
    },
    watch: {
      "birthDate.month"() {
        this.uploadDaysList();
      },
      "birthDate.year"(newYear, oldYear) {
        this.uploadDaysList();
        let currentYear = new Date().getFullYear();
        if (currentYear < newYear) {
          this.birthDate.year = currentYear;
        }
      },
      "user.email"(newEmail, oldEmail) {
        const regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$/i;
        this.validations.emailValid = newEmail.search(regex) !== -1;
      },
      "user.password"(newPassword, oldPassword) {
        this.validations.passwordValid = newPassword.length >= 6 && newPassword.length <= 40;
      },
      passwordConfirmation(newPassword, oldPassword) {
        this.validations.confirmationPasswordValid = newPassword === this.user.password
      },
    }
  }
</script>
