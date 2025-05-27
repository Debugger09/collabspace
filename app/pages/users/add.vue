<!-- pages/users/add.vue -->
<template>
  <Add
    title="Ajouter un utilisateur"
    backUrl="/users"
    :error="error"
    :success="success"
    :loading="loading"
    @submit="handleAdd"
  >
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Nom</label>
        <input v-model="lastName" required class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Prénom</label>
        <input v-model="firstName" required class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-2">Email</label>
        <input v-model="email" type="email" required class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-2">Mot de passe</label>
        <input v-model="password" type="password" required class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Téléphone</label>
        <input v-model="phone" class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Poste</label>
        <input v-model="job" class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition" />
      </div>
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-2">Rôle</label>
        <select v-model="role" required class="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition">
          <option value="" disabled>Choisir un rôle</option>
          <option value="ADMIN">Administrateur</option>
          <option value="CHEF_PROJET">Chef de projet</option>
          <option value="MEMBRE_PROJET">Membre</option>
        </select>
      </div>
    </div>
  </Add>
</template>

<script setup>
import Add from '~/components/add.vue'
import { ref } from 'vue'
import { useRuntimeConfig, useCookie } from '#app'
import { useRouter } from 'vue-router'

const emit = defineEmits()

const config = useRuntimeConfig()
const router = useRouter()

// Champs
const lastName = ref('')
const firstName = ref('')
const email = ref('')
const password = ref('')
const phone = ref('')
const job = ref('')
const role = ref('')

// États
const error = ref('')
const success = ref('')
const loading = ref(false)

const handleAdd = async () => {
  error.value = ''
  success.value = ''
  loading.value = true

  try {
    await $fetch(`${config.public.apiBase}/user/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: {
        lastName: lastName.value,
        firstName: firstName.value,
        email: email.value,
        password: password.value,
        phone: phone.value,
        job: job.value,
        role: role.value
      }
    })

    success.value = 'Utilisateur ajouté avec succès !'
    setTimeout(() => router.push('/users'), 1000)

  } catch (err) {
    console.error(err)
    error.value = err?.data?.message || "Erreur lors de l'ajout de l'utilisateur."
  } finally {
    loading.value = false
  }
}
</script>
