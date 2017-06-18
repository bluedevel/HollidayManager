<template>
    <div>
        {{ message }}
        <button class="btn" v-on:click="test">Test</button>
    </div>
</template>

<script>
    import HTTP from '../../http'

    const data = {
        message: "Test Login"
    };

    function test() {
        HTTP.get('/login')
            .then(() => {
                data.message = 'Success';
            })
            .catch((err) => {
                data.message = 'Error';
                console.log('Failed to log in!', err);
            });
    }

    export default {
        data: () => data,
        methods: {
            test
        }
    }
</script>