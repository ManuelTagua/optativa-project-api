const BASE_URL = "http://localhost:8080/api/v1";

async function loadDevelopers() {
    const msg = document.getElementById("loadDevMsg");
    const list = document.getElementById("developersList");

    msg.innerText = "";
    list.innerHTML = "Cargando developers...";

    try {
        const res = await fetch(`${BASE_URL}/developers`);

        if (!res.ok) {
            msg.className = "msg error";
            msg.innerText = "Error al cargar developers.";
            list.innerHTML = "";
            return;
        }

        const data = await res.json();

        if (data.length === 0) {
            list.innerHTML = "<p>No hay developers registrados.</p>";
            return;
        }

        list.innerHTML = "";
        data.forEach(d => {
            const div = document.createElement("div");
            div.className = "card";
            div.innerHTML = `
                <strong>ID:</strong> ${d.id}<br>
                <strong>Nombre:</strong> ${d.name} ${d.surname}<br>
                <strong>Email:</strong> ${d.email || "-"}<br>
                <strong>LinkedIn:</strong> ${d.linkedinUrl || "-"}<br>
                <strong>GitHub:</strong> ${d.githubUrl || "-"}<br>
                <hr>
            `;
            list.appendChild(div);
        });

        msg.className = "msg ok";
        msg.innerText = `Cargados ${data.length} developers.`;

    } catch (err) {
        console.error(err);
        msg.className = "msg error";
        msg.innerText = "Error de conexión al cargar developers.";
        list.innerHTML = "";
    }
}

async function createDeveloper() {
    const name      = document.getElementById("devName").value.trim();
    const surname   = document.getElementById("devSurname").value.trim();
    const email     = document.getElementById("devEmail").value.trim();
    const linkedin  = document.getElementById("devLinkedin").value.trim();
    const github    = document.getElementById("devGithub").value.trim();

    const msg = document.getElementById("createDevMsg");
    const resultDiv = document.getElementById("createdDeveloper");

    msg.innerText = "";
    resultDiv.innerHTML = "";

    if (!name || !surname) {
        msg.className = "msg error";
        msg.innerText = "Nombre y apellidos son obligatorios.";
        return;
    }

    const body = {
        name: name,
        surname: surname,
        email: email || null,
        linkedinUrl: linkedin || null,
        githubUrl: github || null
    };

    try {
        const res = await fetch(`${BASE_URL}/developers`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        if (res.status !== 201) {
            const txt = await res.text();
            console.error("Error POST /developers:", txt);
            msg.className = "msg error";
            msg.innerText = "Error al crear developer.";
            return;
        }

        const created = await res.json(); // DeveloperDTO
        msg.className = "msg ok";
        msg.innerText = "Developer creado correctamente.";

        resultDiv.innerHTML = `
            <h3>Developer creado</h3>
            <p>
                <strong>ID:</strong> ${created.id}<br>
                <strong>Nombre:</strong> ${created.name} ${created.surname}<br>
                <strong>Email:</strong> ${created.email || "-"}<br>
                <strong>LinkedIn:</strong> ${created.linkedinUrl || "-"}<br>
                <strong>GitHub:</strong> ${created.githubUrl || "-"}<br>
            </p>
        `;

        loadDevelopers();

    } catch (err) {
        console.error(err);
        msg.className = "msg error";
        msg.innerText = "Error de conexión al crear developer.";
    }
}

async function deleteDeveloper() {
    const idStr = document.getElementById("deleteDevId").value.trim();
    const msg = document.getElementById("deleteDevMsg");

    msg.innerText = "";

    const id = parseInt(idStr);
    if (isNaN(id)) {
        msg.className = "msg error";
        msg.innerText = "ID inválido.";
        return;
    }

    try {
        const res = await fetch(`${BASE_URL}/developers/${id}`, {
            method: "DELETE"
        });

        if (res.status === 204) {
            msg.className = "msg ok";
            msg.innerText = `Developer con id ${id} eliminado correctamente.`;
            loadDevelopers();
        } else if (res.status === 404) {
            msg.className = "msg error";
            msg.innerText = `No existe ningún developer con id ${id}.`;
        } else {
            const txt = await res.text();
            console.error("Error DELETE /developers:", txt);
            msg.className = "msg error";
            msg.innerText = "Error al eliminar developer.";
        }

    } catch (err) {
        console.error(err);
        msg.className = "msg error";
        msg.innerText = "Error de conexión al eliminar developer.";
    }
}
