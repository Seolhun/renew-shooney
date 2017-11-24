<template>
  <div class="container">
    <div class="row margin-top-30 text-center">
      <div class="col-xs-12 col-sm-12">
        <img
          src="~assets/img/logo2.png"
          class="max-height-100"
        >
      </div>
    </div>

    <div class="row margin-top-30 text-center">
      <div class="col-xs-12 col-sm-12">
        <!-- login form -->
        <form class="ui form loginForm" @submit.prevent="checkCreds">
          <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
            <input class="form-control" name="username" placeholder="Username" type="text" v-model="username">
          </div>

          <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
            <input class="form-control" name="password" placeholder="Password" type="password" v-model="password">
          </div>

          <div>
            With Github: <a href="http://github.com/login/oauth/authorize?client_id=05096350eaddf80dbd34">click here</a>
          </div>
          <p>
            App Name : {{ $route.params.appName }}
          </p>
          <p>
            Code Value : {{ $route.query.code }}
          </p>
          <p>
            ACCESS_TOKEN : {{ access_token }}
            <button class="btn btn-aqua" @click="getToken">getToken</button>
          </p>


          <button type="submit" v-bind:class="'btn btn-primary btn-lg ' + loading">Submit</button>
        </form>

        <!-- errors -->
        <div v-if=response class="text-red"><p>{{response}}</p></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        section: 'Login',
        appName: this.$route.params.appName,
        loading: '',
        username: '',
        password: '',
        response: '',
        clientId: '05096350eaddf80dbd34',
        clientSecret: 'a2752bd252c43ef3d93f6ec4ac29533f8ff0a087',
        code: this.$route.query.code,
        access_token: ''
      }
    },
    methods: {
      getToken () {
        this.$http.post('https://github.com/login/oauth/access_token', {
          client_id: this.clientId,
          client_secret: this.clientSecret,
          code: this.$route.query.code
        })
          .then(response => {
            this.gitMarkdown = response.data
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      toggleLoading () {
        this.loading = (this.loading === '') ? 'loading' : ''
      },
      resetResponse () {
        this.response = ''
      }
    }
  }
</script>

<style>
</style>
