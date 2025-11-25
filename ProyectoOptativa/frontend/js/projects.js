const BASE_URL = "http://localhost:8080/api/v1";

async function loadProjects() {
    const container = document.getElementById("projectsList");
    container.innerHTML = "Cargando...";

    try {
        const res = await fetch(`${BASE_URL}/projects`);

        if (!res.ok) {
            container.innerHTML = "Error al obtener proyectos.";
            return;
        }

        const data = await res.json();
        container.innerHTML = "";

        data.forEach(p => {
            const div = document.createElement("div");
            div.className = "project-card";

            div.innerHTML = `
                <div class="project-name">${p.name}</div>
                <div class="project-info">
                    <strong>ID:</strong> ${p.id}<br>
                    <strong>Descripción:</strong> ${p.description}<br>
                    <strong>Estado:</strong> ${p.status?.name || "Sin estado"}<br>
                </div>
            `;

            container.appendChild(div);
        });

    } catch (err) {
        container.innerHTML = "Error al conectar con el servidor.";
        console.error(err);
    }
}

async function createProject() {

    const name = document.getElementById("createName").value.trim();
    const description = document.getElementById("createDesc").value.trim();
    const demoUrl = document.getElementById("createDemo").value.trim();
    const picture = document.getElementById("createPic").value.trim();
    
    const status = document.getElementById("createStatus").value;

    const msg = document.getElementById("createMsg");

    const project = {
        name,
        description,
        demoUrl,
        picture,
        startDate: "2024-01-01",
        endDate: "2024-12-31"
    };

    if (status !== "") {
        project.status = { id: parseInt(status) };
    }

    try {
        const res = await fetch(`${BASE_URL}/projects`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(project)
        });

        if (res.status === 201) {
            msg.className = "msg ok";
            msg.innerText = "Proyecto creado correctamente.";
            loadProjects();
        } else {
            msg.className = "msg error";
            msg.innerText = "Error al crear proyecto.";
        }

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error al conectar con el servidor.";
        console.error(err);
    }
}

async function updateProject() {
    const id = parseInt(document.getElementById("editId").value);
    const name = document.getElementById("editName").value.trim();
    const description = document.getElementById("editDesc").value.trim();
    const demoUrl = document.getElementById("editDemo").value.trim();
    const picture = document.getElementById("editPic").value.trim();
    
    const status = document.getElementById("editStatus").value;

    const msg = document.getElementById("editMsg");

    if (isNaN(id)) {
        msg.className = "msg error";
        msg.innerText = "Debes indicar un ID válido.";
        return;
    }

    const project = {
        name,
        description,
        demoUrl,
        picture,
        startDate: "2024-01-01",
        endDate: "2024-12-31"
    };

    if (status !== "") {
        project.status = { id: parseInt(status) };
    }

    try {
        const res = await fetch(`${BASE_URL}/projects/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(project)
        });

        if (res.ok) {
            msg.className = "msg ok";
            msg.innerText = "Proyecto actualizado.";
            loadProjects();
        } else if (res.status === 404) {
            msg.className = "msg error";
            msg.innerText = "Proyecto no encontrado.";
        } else {
            msg.className = "msg error";
            msg.innerText = "Error al actualizar.";
        }

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error al conectar.";
        console.error(err);
    }
}

async function deleteProject() {
    const id = parseInt(document.getElementById("deleteId").value);
    const msg = document.getElementById("deleteMsg");

    if (isNaN(id)) {
        msg.className = "msg error";
        msg.innerText = "ID inválido.";
        return;
    }

    try {
        const res = await fetch(`${BASE_URL}/projects/${id}`, {
            method: "DELETE"
        });

        if (res.status === 204) {
            msg.className = "msg ok";
            msg.innerText = "Proyecto eliminado.";
            loadProjects();
        } else {
            msg.className = "msg error";
            msg.innerText = "Proyecto no encontrado.";
        }

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error de conexión.";
        console.error(err);
    }
}

async function searchProjectByWord() {

    const word = document.getElementById("searchWord").value.trim();
    const msg = document.getElementById("searchMsg");
    const countDiv = document.getElementById("searchCount");
    const resultsDiv = document.getElementById("searchResults");

    if (word.length === 0) {
        msg.className = "msg error";
        msg.innerText = "Debes escribir una palabra.";
        countDiv.innerHTML = "";
        resultsDiv.innerHTML = "";
        return;
    }

    resultsDiv.innerHTML = "Buscando...";
    countDiv.innerHTML = "";

    try {
        const res = await fetch(`${BASE_URL}/projects/${encodeURIComponent(word)}`);

        if (res.status === 404) {
            msg.className = "msg error";
            msg.innerText = "No se encontraron proyectos.";
            countDiv.innerHTML = "";
            resultsDiv.innerHTML = "";
            return;
        }

        if (!res.ok) {
            msg.className = "msg error";
            msg.innerText = "Error inesperado.";
            countDiv.innerHTML = "";
            resultsDiv.innerHTML = "";
            return;
        }

        const data = await res.json();

        msg.className = "msg ok";
        msg.innerText = "";

        countDiv.innerHTML = `Encontrados <strong>${data.length}</strong> proyectos.`;
        resultsDiv.innerHTML = "";

        data.forEach(p => {
            const div = document.createElement("div");
            div.className = "project-card";

            div.innerHTML = `
                <div class="project-name">${p.name}</div>
                <div class="project-info">
                    <strong>ID:</strong> ${p.id}<br>
                    <strong>Descripción:</strong> ${p.description}<br>
                    <strong>Estado:</strong> ${p.status?.name || "Sin estado"}<br>
                </div>
            `;

            resultsDiv.appendChild(div);
        });

    } catch (err) {
        msg.className = "msg error";
        msg.innerText = "Error de conexión.";
        countDiv.innerHTML = "";
        resultsDiv.innerHTML = "";
        console.error(err);
    }
}
