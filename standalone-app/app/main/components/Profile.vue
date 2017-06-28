<template>
    <div>
        {{ user.username }}
    </div>
</template>

<script>
    import HTTP from '../../http'

    const data = {
        user: {}
    };

    function mounted() {

        HTTP.get('/users/current')
            .then((resp) => {
                data.user = resp.data;
            })
            .catch((err) => {
                console.log('Failed to load user!', err);
            });

    }

    export default {
        data: () => data,
        mounted
    }
</script>