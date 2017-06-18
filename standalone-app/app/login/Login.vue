<template>
    <div id="wrapper">
        <div id="login-card" class="col s12 m7 center-align">
            <div class="card horizontal">
                <div class="card-image">
                    <img src="http://lorempixel.com/100/190/nature/6">
                </div>
                <div class="card-stacked">
                    <div class="card-content">
                        <input v-model="username" placeholder="Username" type="text"/>
                        <input v-model="password" placeholder="Password" type="password"/>
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
        username: '',
        password: ''
    };

    function login() {
        console.log(data);
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
</style>