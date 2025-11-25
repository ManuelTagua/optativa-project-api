const BASE_URL = "http://localhost:8080/api/v1";

async function loadTechnologies() {
    const container = document.getElementById("techList");
    container.innerHTML = "Cargando...";

    try {
        const res = await fetch(`${BASE_URL}/technologies`);
        if (!res.ok) {
            container.innerHTML = "Error al cargar technologies.";
            return;
        }

        const data = await res.json();
        container.innerHTML = "";

        data.forEach(t => {
            const div = document.createElement("div");
            div.className = "project-card";
            div.innerHTML = `
                <strong>ID:</strong> ${t.id}<br>
                <strong>Nombre:</strong> ${t.name}
            `;
            container.appendChild(div);
        });

    } catch (err) {
        console.error(err);
        container.innerHTML = "Error de conexión.";
    }
}

async function createTechnology() {
    const name = document.getElementById("techName").value.trim();
    const msg = document.getElementById("techCreateMsg");

    if (name.length === 0) {
        msg.className = "msg error";
        msg.innerText = "Debes escribir un nombre.";
        return;
    }

    try {
        const res = await fetch(`${BASE_URL}/technologies`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name })
        });

        if (res.status === 201) {
            msg.className = "msg ok";
            msg.innerText = "Technology creada correctamente.";
            loadTechnologies();
        } else {
            msg.className = "msg error";
            msg.innerText = "Error al crear.";
        }

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error de conexión.";
    }
}

async function deleteTechnology() {
    const id = parseInt(document.getElementById("techDeleteId").value);
    const msg = document.getElementById("techDeleteMsg");

    if (isNaN(id)) {
        msg.className = "msg error";
        msg.innerText = "ID inválido.";
        return;
    }

    try {
        const res = await fetch(`${BASE_URL}/technologies/${id}`, {
            method: "DELETE"
        });

        if (res.status === 204) {
            msg.className = "msg ok";
            msg.innerText = "Technology eliminada.";
            loadTechnologies();
        } else {
            msg.className = "msg error";
            msg.innerText = "Technology no encontrada.";
        }

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error de conexión.";
    }
}
