<template>
    <div>
        <ul v-if="user" class="collection with-header">
            <li class="collection-header"><h4>{{ user.firstName }} {{user.lastName }}</h4></li>
            <li class="collection-item"><div>Username<span class="secondary-content">{{ user.username }}</span></div></li>
            <li class="collection-item"><div>Role<span class="secondary-content">{{ user.role }}</span></div></li>
            <li class="collection-item"><div>Department<span class="secondary-content">{{ user.department.name }}</span></div></li>
            <li class="collection-item"><div>Total vacation days<span class="secondary-content">{{ user.vacationDays }}</span></div></li>
        </ul>
    </div>
</template>

<script>
    import HTTP from '../../http'

    const data = {
        user: {
            department: {}
        }
    };

    function created() {

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
        created
    }
</script>