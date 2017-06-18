<template>
    <div id="wrapper">
        <div id="login-card" class="col s12 m7">
            <div class="card horizontal">
                <div class="card-image valign-wrapper">
                    <i class="material-icons lock">lock</i>
                </div>
                <div class="card-stacked">
                    <div class="card-content">
                        <input v-model="auth.username" placeholder="Username" type="text"/>
                        <input v-model="auth.password" placeholder="Password" type="password"/>
                    </div>
                    <div class="card-action">
                        <a v-on:click="login" href="#">Login</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import HTTP from '../http'

    const data = {
        debug: '',
        auth: {
            username: '',
            password: ''
        }
    };

    function login(e) {
        console.log(data.auth.username);
        HTTP.defaults.auth = data.auth;
        HTTP.get('/login')
            .then(() => {
                console.log(this.$route.query.redirect);
                this.$router.push(this.$route.query.redirect);
            })
            .catch((err) => {
                console.log('Failed to log in!', err);
            });

        // ensure there ist no reload to mess up the routing
        e.preventDefault();
    }

    export default {
        data: () => data,
        methods: {
            login
        }
    }
</script>

<style>
    #wrapper {
        display: flex;
    }

    #login-card {
        width: 600px;
        margin: auto;
    }

    .lock {
        font-size: 60px;
    }
</style>