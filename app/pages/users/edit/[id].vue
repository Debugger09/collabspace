<template>
  <Add
    title="Modifier un utilisateur"
    backUrl="/users"
    :error="error"
    :success="success"
    :loading="loading"
    @submit="handleUpdate"
  >
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Nom</label>
        <input v-model="lastName" required class="w-full border border-gray-300 rounded-lg px-4 py-3" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Prénom</label>
        <input v-model="firstName" required class="w-full border border-gray-300 rounded-lg px-4 py-3" />
      </div>
      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-2">Email</label>
        <input v-model="email" type="email" required class="w-full border border-gray-300 rounded-lg px-4 py-3" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Téléphone</label>
        <input v-model="phone" class="w-full border border-gray-300 rounded-lg px-4 py-3" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Poste</label>
        <input v-model="job" class="w-full border border-gray-300 rounded-lg px-4 py-3" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Rôle</label>
        <select v-model="role" required class="w-full border border-gray-300 rounded-lg px-4 py-3">
          <option value="" disabled>Choisir un rôle</option>
          <option value="ADMIN">Administrateur</option>
          <option value="CHEF_PROJET">Chef de projet</option>
          <option value="MEMBRE_PROJET">Membre</option>
        </select>
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Statut</label>
        <select v-model="status" class="w-full border border-gray-300 rounded-lg px-4 py-3">
          <option :value="true">Actif</option>
          <option :value="false">Inactif</option>
        </select>
      </div>
    </div>
  </Add>
</template>

<script setup>
import Add from '~/components/add.vue'
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useRuntimeConfig } from '#app'

const route = useRoute()
const router = useRouter()
const config = useRuntimeConfig()

const error = ref('')
const success = ref('')
const loading = ref(false)

// Champs
const lastName = ref('')
const firstName = ref('')
const email = ref('')
const phone = ref('')
const job = ref('')
const role = ref('')
const status = ref(true)

const id = route.params.id

onMounted(async () => {
  try {
    loading.value = true
    // Appel simplifié, le plugin ajoute le token automatiquement
    const user = await $fetch(`${config.public.apiBase}/user/${id}`)
    lastName.value = user.lastName
    firstName.value = user.firstName
    email.value = user.email
    phone.value = user.phone
    job.value = user.job
    role.value = user.role
    status.value = user.status
  } catch (err) {
    error.value = "Impossible de charger l'utilisateur."
  } finally {
    loading.value = false
  }
})

const handleUpdate = async () => {
  error.value = ''
  success.value = ''
  loading.value = true
  try {
    await $fetch(`${config.public.apiBase}/user/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: {
        lastName: lastName.value,
        firstName: firstName.value,
        email: email.value,
        phone: phone.value,
        job: job.value,
        role: role.value,
        status: status.value
      }
    })
    success.value = 'Utilisateur modifié avec succès !'
    setTimeout(() => router.push('/users'), 1000)
  } catch (err) {
    error.value = err?.data?.message || "Erreur lors de la modification."
  } finally {
    loading.value = false
  }
}
</script>