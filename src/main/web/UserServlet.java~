package web;

import dao.*;
import entity.Role;
import entity.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @EJB
    private RepositoryUser userRepository;

    @EJB
    private RepositoryR roleRepository;

    @EJB
    private Auth authRepository;

    @Override
    public void init() throws ServletException {
        System.out.println(">> userRepository = " + userRepository);
        System.out.println(">> roleRepository = " + roleRepository);
        System.out.println(">> authRepository = " + authRepository);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action") == null ? "list" : req.getParameter("action") ;


        // Liste des actions qui ne nécessitent pas d'authentification
        boolean isPublicAction = "login".equals(action);

        // Vérifier si l'utilisateur est connecté pour les actions protégées
        if (!isPublicAction && authRepository.getCurrentUser() == null) {
            // Rediriger vers la page de login si l'utilisateur n'est pas connecté
            req.setAttribute("errorMessage", "Veuillez vous connecter pour accéder à cette page");

            // Si vous avez déjà une page login.jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        RequestDispatcher dispatcher ;
        switch (action){
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                User currentUser = authRepository.getCurrentUser();

                if (currentUser != null && currentUser.getId() == id) {
                    req.getSession().setAttribute("errorMessage", "Vous ne pouvez pas supprimer votre propre compte.");
                    resp.sendRedirect("?action=list");
                } else {
                    userRepository.delete(id);
                    if (currentUser != null) {
                        authRepository.addUserAction("Deleted user with ID: " + id);
                    }
                    resp.sendRedirect("?action=list");
                }
                break;
            case "add":
                List<Role> roles = roleRepository.getAll();
                req.setAttribute("roles",roles);
                dispatcher = req.getRequestDispatcher("views/User/add.jsp");
                dispatcher.forward(req,resp);
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                User user = userRepository.getById(idEdit);
                req.setAttribute("user",user);
                dispatcher = req.getRequestDispatcher("views/User/edit.jsp");
                dispatcher.forward(req,resp);
                break;
            case "login":
                if (req.getParameter("id") != null) {
                    int userId = Integer.parseInt(req.getParameter("id"));
                    User loginUser = userRepository.getById(userId);
                    authRepository.setCurrentUser(loginUser);
                    authRepository.addUserAction("User logged in");
                    resp.sendRedirect("?action=list");
                } else {
                    // Afficher la page de login
                    dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
                }
                break;
            case "logout":
                authRepository.clearSession();
                resp.sendRedirect("?action=list");
                break;
            default:
                req.setAttribute("listUser", userRepository.getAll());
                req.setAttribute("currentUser", authRepository.getCurrentUser());
                req.setAttribute("userActions", authRepository.getUserActions());
                dispatcher = req.getRequestDispatcher("views/User/list.jsp"); //
                dispatcher.forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        User user;
        RequestDispatcher dispatcher;
        switch (action) {
            case "save":
                user = User.builder()
                        .prenom(req.getParameter("prenom"))
                        .nom(req.getParameter("nom"))
                        .age(Integer.parseInt(req.getParameter("age")))
                        .username(req.getParameter("username"))
                        .password(req.getParameter("password"))
                        .role(roleRepository.getById(Integer.parseInt(req.getParameter("role"))))
                        .build();
                userRepository.insert(user);
                resp.sendRedirect("?action=list");
                break;
            case "update":
                user = User.builder()
                        .id(Integer.parseInt(req.getParameter("id")))
                        .prenom(req.getParameter("prenom"))
                        .nom(req.getParameter("nom"))
                        .username(req.getParameter("username"))
                        .password(req.getParameter("password"))
                        .age(Integer.parseInt(req.getParameter("age")))
                        .build();
                userRepository.update(user);
                resp.sendRedirect("?action=list");
                break;

            case "doLogin":
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                boolean loginSuccess = authRepository.login(username, password);

                if (loginSuccess) {
                    resp.sendRedirect("?action=list");
                } else {
                    req.setAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect");
                    dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
                }
                break;
        }

    }
}
