<template>
  <Header>
    <div>
      <h1 class="text-2xl font-bold text-gray-800">Tableau de bord</h1>
      <p class="mt-4 text-gray-600">Bienvenue sur votre tableau de bord. Voici un aperçu de vos activités récentes.</p>

      <!-- Contenu du tableau de bord -->
      <div class="mt-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <!-- Exemple de carte -->
        <div class="bg-white shadow-md rounded-lg p-4">
          <h2 class="text-lg font-semibold text-blue-600">Projets actifs</h2>
          <p class="mt-2 text-gray-600">Vous avez 5 projets en cours.</p>
        </div>

        <div class="bg-white shadow-md rounded-lg p-4">
          <h2 class="text-lg font-semibold text-blue-600">Tâches à faire</h2>
          <p class="mt-2 text-gray-600">3 tâches sont en attente de validation.</p>
        </div>

        <div class="bg-white shadow-md rounded-lg p-4">
          <h2 class="text-lg font-semibold text-blue-600">Messages non lus</h2>
          <p class="mt-2 text-gray-600">Vous avez 2 nouveaux messages.</p>
        </div>
      </div>
    </div>
  </Header>
</template>

<script setup>
import Header from '~/components/header.vue';
</script>